```text
2023-10-23T00:33:17.482+09:00  INFO 17486 --- [           main] reactor.Flux.MonoFlatMapMany.1           : onSubscribe(MonoFlatMapMany.FlatMapManyMain)
2023-10-23T00:33:17.484+09:00  INFO 17486 --- [           main] reactor.Flux.MonoFlatMapMany.1           : request(unbounded)
2023-10-23T00:33:18.059+09:00  INFO 17486 --- [o-auto-1-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2023-10-23T00:33:18.059+09:00  INFO 17486 --- [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2023-10-23T00:33:18.061+09:00  INFO 17486 --- [o-auto-1-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
2023-10-23T00:33:18.096+09:00  WARN 17486 --- [o-auto-1-exec-1] ocalVariableTableParameterNameDiscoverer : Using deprecated '-debug' fallback for parameter name resolution. Compile the affected code with '-parameters' instead or avoid its introspection: com.heowc.api.MemberApi
2023-10-23T00:33:18.102+09:00  INFO 17486 --- [o-auto-1-exec-1] com.heowc.api.MemberApi                  : stream all
2023-10-23T00:33:18.272+09:00  INFO 17486 --- [pool-4-thread-1] com.heowc.api.MemberApi                  : send sse-data. data=Member{id=1, createdAt=2023-10-23T00:33:16.541073}
2023-10-23T00:33:18.345+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onNext(ServerSentEvent [id = '1', event='null', retry=null, comment='null', data={"id":1,"createdAt":"2023-10-23T00:33:16.541073"}])
2023-10-23T00:33:18.441+09:00  INFO 17486 --- [pool-4-thread-1] com.heowc.api.MemberApi                  : send sse-data. data=Member{id=2, createdAt=2023-10-23T00:33:16.541073}
2023-10-23T00:33:18.443+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onNext(ServerSentEvent [id = '2', event='null', retry=null, comment='null', data={"id":2,"createdAt":"2023-10-23T00:33:16.541073"}])
2023-10-23T00:33:18.544+09:00  INFO 17486 --- [pool-4-thread-1] com.heowc.api.MemberApi                  : send sse-data. data=Member{id=3, createdAt=2023-10-23T00:33:16.541073}
2023-10-23T00:33:18.546+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onNext(ServerSentEvent [id = '3', event='null', retry=null, comment='null', data={"id":3,"createdAt":"2023-10-23T00:33:16.541073"}])
2023-10-23T00:33:18.650+09:00  INFO 17486 --- [pool-4-thread-1] com.heowc.api.MemberApi                  : send sse-data. data=Member{id=4, createdAt=2023-10-23T00:33:16.541073}
2023-10-23T00:33:18.654+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onNext(ServerSentEvent [id = '4', event='null', retry=null, comment='null', data={"id":4,"createdAt":"2023-10-23T00:33:16.541073"}])
2023-10-23T00:33:18.753+09:00  INFO 17486 --- [pool-4-thread-1] com.heowc.api.MemberApi                  : send sse-data. data=Member{id=5, createdAt=2023-10-23T00:33:16.541073}
2023-10-23T00:33:18.755+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onNext(ServerSentEvent [id = '5', event='null', retry=null, comment='null', data={"id":5,"createdAt":"2023-10-23T00:33:16.541073"}])
2023-10-23T00:33:18.870+09:00  INFO 17486 --- [ctor-http-nio-2] reactor.Flux.MonoFlatMapMany.1           : onComplete()

```