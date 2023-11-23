Payara fails to generate OpenAPI document when smallrye-graphql-servlet is on the classpath
===========================================================================================

## Problem

When the latest versions of io.smallrye:smallrye-graphql-servlet is included in .war
accessing the `http://localhost:8080/openapi` endpoint results in:

```
[2023-11-23T10:07:43.664+0100] [] [SEVERE] [] [org.glassfish.jersey.server.ServerRuntime$Responder] [tid: _ThreadID=74 _ThreadName=http-thread-pool::http-listener(2)] [timeMillis: 1700730463664] [levelValue: 1000] [[
  Error while closing the output stream in order to commit response.
org.glassfish.jersey.server.ContainerException: java.util.concurrent.ExecutionException: java.lang.RuntimeException: An error occurred while creating the OpenAPI document.
        at org.glassfish.jersey.servlet.internal.ResponseWriter.getResponseContext(ResponseWriter.java:278)
        at org.glassfish.jersey.servlet.internal.ResponseWriter.writeResponseStatusAndHeaders(ResponseWriter.java:128)
        at org.glassfish.jersey.server.ServerRuntime$Responder$1.getOutputStream(ServerRuntime.java:667)
        at org.glassfish.jersey.message.internal.CommittingOutputStream.commitStream(CommittingOutputStream.java:171)
        at org.glassfish.jersey.message.internal.CommittingOutputStream.flushBuffer(CommittingOutputStream.java:276)
        at org.glassfish.jersey.message.internal.CommittingOutputStream.commit(CommittingOutputStream.java:232)
        at org.glassfish.jersey.message.internal.CommittingOutputStream.close(CommittingOutputStream.java:247)
        at org.glassfish.jersey.message.internal.OutboundMessageContext.close(OutboundMessageContext.java:842)
        at org.glassfish.jersey.server.ContainerResponse.close(ContainerResponse.java:389)
        at org.glassfish.jersey.server.ServerRuntime$Responder.writeResponse(ServerRuntime.java:749)
        at org.glassfish.jersey.server.ServerRuntime$Responder.processResponse(ServerRuntime.java:385)
        at org.glassfish.jersey.server.ServerRuntime$Responder.processResponseWithDefaultExceptionMapper(ServerRuntime.java:633)
        at org.glassfish.jersey.server.ServerRuntime$Responder.process(ServerRuntime.java:458)
        at org.glassfish.jersey.server.ServerRuntime$1.run(ServerRuntime.java:269)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:248)
        at org.glassfish.jersey.internal.Errors$1.call(Errors.java:244)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:292)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:274)
        at org.glassfish.jersey.internal.Errors.process(Errors.java:244)
        at org.glassfish.jersey.process.internal.RequestScope.runInScope(RequestScope.java:265)
        at org.glassfish.jersey.server.ServerRuntime.process(ServerRuntime.java:240)
        at org.glassfish.jersey.server.ApplicationHandler.handle(ApplicationHandler.java:697)
        at org.glassfish.jersey.servlet.WebComponent.serviceImpl(WebComponent.java:394)
        at org.glassfish.jersey.servlet.WebComponent.service(WebComponent.java:346)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:357)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:311)
        at org.glassfish.jersey.servlet.ServletContainer.service(ServletContainer.java:205)
        at org.apache.catalina.core.StandardWrapper.service(StandardWrapper.java:1554)
        at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:259)
        at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:166)
        at org.apache.catalina.core.StandardPipeline.doInvoke(StandardPipeline.java:757)
        at org.apache.catalina.core.StandardPipeline.invoke(StandardPipeline.java:577)
        at com.sun.enterprise.web.WebPipeline.invoke(WebPipeline.java:99)
        at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:158)
        at org.apache.catalina.connector.CoyoteAdapter.doService(CoyoteAdapter.java:372)
        at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:239)
        at com.sun.enterprise.v3.services.impl.ContainerMapper$HttpHandlerCallable.call(ContainerMapper.java:520)
        at com.sun.enterprise.v3.services.impl.ContainerMapper.service(ContainerMapper.java:217)
        at org.glassfish.grizzly.http.server.HttpHandler.runService(HttpHandler.java:174)
        at org.glassfish.grizzly.http.server.HttpHandler.doHandle(HttpHandler.java:153)
        at org.glassfish.grizzly.http.server.HttpServerFilter.handleRead(HttpServerFilter.java:196)
        at org.glassfish.grizzly.filterchain.ExecutorResolver$9.execute(ExecutorResolver.java:88)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeFilter(DefaultFilterChain.java:246)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.executeChainPart(DefaultFilterChain.java:178)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.execute(DefaultFilterChain.java:118)
        at org.glassfish.grizzly.filterchain.DefaultFilterChain.process(DefaultFilterChain.java:96)
        at org.glassfish.grizzly.ProcessorExecutor.execute(ProcessorExecutor.java:51)
        at org.glassfish.grizzly.nio.transport.TCPNIOTransport.fireIOEvent(TCPNIOTransport.java:510)
        at org.glassfish.grizzly.strategies.AbstractIOStrategy.fireIOEvent(AbstractIOStrategy.java:82)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy.run0(WorkerThreadIOStrategy.java:83)
        at org.glassfish.grizzly.strategies.WorkerThreadIOStrategy$WorkerThreadRunnable.run(WorkerThreadIOStrategy.java:101)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.doWork(AbstractThreadPool.java:535)
        at org.glassfish.grizzly.threadpool.AbstractThreadPool$Worker.run(AbstractThreadPool.java:515)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: java.util.concurrent.ExecutionException: java.lang.RuntimeException: An error occurred while creating the OpenAPI document.
        at java.base/java.util.concurrent.CompletableFuture.reportGet(CompletableFuture.java:395)
        at java.base/java.util.concurrent.CompletableFuture.get(CompletableFuture.java:1999)
        at org.glassfish.jersey.servlet.internal.ResponseWriter.getResponseContext(ResponseWriter.java:276)
        ... 53 more
Caused by: java.lang.RuntimeException: An error occurred while creating the OpenAPI document.
        at fish.payara.microprofile.openapi.impl.OpenAPISupplier.get(OpenAPISupplier.java:158)
        at fish.payara.microprofile.openapi.impl.OpenApiService.getDocument(OpenApiService.java:149)
        at fish.payara.microprofile.openapi.impl.rest.app.service.OpenApiResource.getResponse(OpenApiResource.java:84)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:566)
        at org.glassfish.jersey.server.model.internal.ResourceMethodInvocationHandlerFactory.lambda$static$0(ResourceMethodInvocationHandlerFactory.java:52)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher$1.run(AbstractJavaResourceMethodDispatcher.java:134)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.invoke(AbstractJavaResourceMethodDispatcher.java:177)
        at org.glassfish.jersey.server.model.internal.JavaResourceMethodDispatcherProvider$ResponseOutInvoker.doDispatch(JavaResourceMethodDispatcherProvider.java:176)
        at org.glassfish.jersey.server.model.internal.AbstractJavaResourceMethodDispatcher.dispatch(AbstractJavaResourceMethodDispatcher.java:81)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:478)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:400)
        at org.glassfish.jersey.server.model.ResourceMethodInvoker.apply(ResourceMethodInvoker.java:81)
        at org.glassfish.jersey.server.ServerRuntime$1.run(ServerRuntime.java:261)
        ... 40 more
Caused by: java.lang.IllegalStateException: Duplicate key kotlin.Metadata (attempted merging values AnnotationTypeImpl{name='kotlin.Metadata', annotations=[ kotlin.annotation.Retention kotlin.annotation.Target java.lang.annotation.Retention java.lang.annotation.Target kotlin.Metadata kotlin.SinceKotlin], subclasses=[], parent=null, interfaces=[], implementors=[], referenced from [ ClassModelImpl{kotlin.jvm.internal.CharSpreadBuilder} ClassModelImpl{kotlin.collections.ArraysKt___ArraysKt$withIndex$2} EnumTypeImpl{kotlinx.metadata.Visibility} ClassModelImpl{kotlin.collections.AbstractCollection} ClassModelImpl{kotlin.collections.ArraysKt} ...
```

The cause seems to be the inclusion of kotlin support in smallrye-graphql. Last version of smallrye-graphql-servlet
that does not exhibit this behaviour is 2.2.3.


## Steps to reproduce

```bash
mvn package
java -jar payara-micro-6.2023.11.jar target/helloworld-1.0.0-SNAPSHOT.war
# access http://localhost:8080/openapi in browser
```

## Environment

The issue has been seen on versions 6.2023.10 and 6.2023.11 of payara-micro running with both OpenJDK versions 11.0.20 and 17.0.8 
