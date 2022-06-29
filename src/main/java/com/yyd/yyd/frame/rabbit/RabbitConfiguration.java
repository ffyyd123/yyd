package com.yyd.yyd.frame.rabbit;


//@Configuration
public class RabbitConfiguration {


//    @Value("${rabbit.host}")
//    private String host;
//    @Value("${rabbit.port}")
//    private int port;
//    @Value("${rabbit.username}")
//    private String userName;
//    @Value("${rabbit.password}")
//    private String password;
//    @Value("${rabbit.vhost}")
//    private String vhost;

    /**
     * mq prop
     */
//    @Value("${rabbit.exchange}")
//    private String exchange;
//
//    @Value("${rabbit.queue.default}")
//    private String defaultQueue;
//    @Value("${rabbit.key.default}")
//    private String defaultKey;


    /**
     * mq delay prop
     */
//    @Value("${rabbit.exchange.delay}")
//    private String delayExchange;
//
//    @Value("${rabbit.queue.delay}")
//    private String delayQueue;
//    @Value("${rabbit.key.delay}")
//    private String delayKey;

//    @Bean
//    public ConnectionFactory rabbitConnectionFactory() {
//        com.rabbitmq.client.ConnectionFactory factory =
//                new com.rabbitmq.client.ConnectionFactory();
//
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUsername(userName);
//        factory.setPassword(password);
//        factory.setVirtualHost(vhost);
//
//        factory.setRequestedHeartbeat(60);
//        factory.setConnectionTimeout(10000);
//        factory.setNetworkRecoveryInterval(10000);
//        factory.setAutomaticRecoveryEnabled(true);
//        factory.setTopologyRecoveryEnabled(true);
//
//        ConnectionFactory connectionFactory = new CachingConnectionFactory(factory);
//        ((CachingConnectionFactory) connectionFactory).setPublisherConfirms(true);
//        ((CachingConnectionFactory) connectionFactory).setPublisherReturns(true);
//        ((CachingConnectionFactory) connectionFactory).setChannelCacheSize(50);
//
//        return connectionFactory;
//    }
//
//    @Bean
//    RabbitAdmin admin() {
//        RabbitAdmin admin = new RabbitAdmin(rabbitConnectionFactory());
//        admin.setIgnoreDeclarationExceptions(false);
//        return admin;
//    }
//
//    @Bean
//    public Exchange exchange() {
//        return new DirectExchange(exchange);
//    }
//
//    @Bean
//    public Queue defaultQueue() {
//        return new Queue(defaultQueue, true);
//    }
//
//    @Bean
//    public Binding bindingDefault() {
//        return BindingBuilder.bind(defaultQueue()).to(exchange()).with(defaultKey).noargs();
//    }
//
//    @Bean(value = "customRabbitListenerContainerFactory")
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(rabbitConnectionFactory());
//        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        factory.setPrefetchCount(10);
//        factory.setConcurrentConsumers(50);
//        factory.setMaxConcurrentConsumers(100);

//        factory.setPrefetchCount(10);
//        factory.setConcurrentConsumers(200);
//        factory.setMaxConcurrentConsumers(500);
//        return factory;
//    }
//
//    @Bean
//    public CustomExchange delayExchange() {
//        Map<String, Object> args = new HashMap<>();
//        args.put("x-delayed-type", "direct");
//        return new CustomExchange(delayExchange, "x-delayed-message", true, false, args);
//    }
//
//    @Bean
//    public Queue delayQueue() {
//        return new Queue(delayQueue, true);
//    }
//
//    @Bean
//    public Binding bindingDelay() {
//        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(delayKey).noargs();
//    }
}
