package com.zzy.eban.config;

import com.zzy.eban.config.security.JwtTokenUtils;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author ZhuZhengYang
 * @description TODO
 * @since 2022/3/27
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @Autowired
    private UserDetailsService userDetailsService;
  /**
   * @description: 添加端点，这样可以在网页上webscoket连接上服务
   * 也就是我们配置websocket的服务地址，并且可以指定是否使用socketJS
   * @author: zzy
   * @data: 2022/3/27 14:04
   */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
        * 将ws/ep路径注册为stomp的断点，用户连接了这个断点就可以进行websocket通信支持sockJS
        * setAllowedOrigins("*"):允许跨域
        *
        * */
        registry.addEndpoint("/ws/ep").setAllowedOrigins("*").withSockJS();
    }
/**
 * @description: 输入通道参数配置
 * @author: zzy
 * @data: 2022/3/27 14:12
 */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
       registration.interceptors(new ChannelInterceptor() {
           @Override
           public Message<?> preSend(Message<?> message, MessageChannel channel) {
               StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
               //判断是否为连接如果是，需要获取Token，并且设置用户对象
               if (StompCommand.CONNECT.equals(accessor.getCommand())){
                   String token = accessor.getFirstNativeHeader("Auth-Token");
                   if (!StringUtils.isEmpty(token)){
                       String authToken = token.substring(tokenHead.length());
                       String name = jwtTokenUtils.getUserNameFromToken(authToken);
                       //token中存在用户名
                       if (!StringUtils.isEmpty(name)){
                           //登录
                           UserDetails userDetails = userDetailsService.loadUserByUsername(name);
                           //验证token是否有效
                           if (jwtTokenUtils.validateToken(authToken,userDetails)){
                               UsernamePasswordAuthenticationToken authenticationToken = new
                                       UsernamePasswordAuthenticationToken(userDetails, null,
                                       userDetails.getAuthorities());

                               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                               accessor.setUser(authenticationToken);
                           }

                       }


                   }

               }
               return message;
           }
       });
    }

    /**
     * @description: 配置消息代理
     * @author: zzy
     * @data: 2022/3/27 14:08
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*配置代理域，可以配置多个配置，代理目的地前缀为/queue,可以在配置域上向客户端推送消息*/
        registry.enableSimpleBroker("/queue");
    }
}
