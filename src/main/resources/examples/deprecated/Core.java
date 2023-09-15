// todo: this code flushes in h2
//	private final RedisConnectionFactory redisConnectionFactory;
//	@Autowired
//	public H2(RedisConnectionFactory redisConnectionFactory) {
//		this.redisConnectionFactory = redisConnectionFactory;
//	}
//	private static final Logger logger = LoggerFactory.getLogger(H2.class);
//	@PreDestroy
//	public void onShutdown() {
//		logger.info("Flushing redis...");
//		redisConnectionFactory.getConnection().serverCommands().flushDb();
//	}
