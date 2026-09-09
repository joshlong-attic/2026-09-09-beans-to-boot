# 2026-09-09-beans-to-boot
hi Spring fans! in this installment we look at some of the amazing opportunities that lay ahead for Spring devs

* proxies & aop
* security
* `java -jar`
* integration (email, messaging, threads)
* virtual threads
* SQL in Spring  `JdbcClient` - `RowMapper` vs `ResultSetExtractor`
* graalvm 
* build docker images out of ur apps so they can be run as services
* lazy connection pools 
* mcp auth 
* gateway as the proxy for both ur UI and the backend APIs that power them



## gateway 
im not sure that I got wind of how u have structured ur UI interactions with the backend. 

I know u mentioned the `javaspecialists.eu` site was classic .jsp, and that u had started this as an add-on to that site. but is it still ? or have u since extracted it out into a separate thing? is the UI server-side rendered? or is it using client-side JS + HTML + CSS (as is the usual these days). If the latter, then id love to ensure ur using:

 * a spring cloud gateway. it acts as a proxy to the your backend APIs and frontend views. u might have ur backend APIs (e.g.: `/meanif/blah`, `/api/meanif/foo`, etc.), proxied under `/api/*`, such that going to `meanif.com/api/meanif/foo` proxies to `backendapi:1234/meanif/foo`, and going to `meanif.com/index.html` goes to `somecdn.com/urapp/12e3232/index.html`. the benefit is that u could serve bot the UI and the backend APIs from one domain. this dramatically simplifies the client code because now u dont have to worry about CORS or, once u setup OAuth for security, managing security. it also has filters so that u can intercept requests that have no token and then redirect u to an auth server in which to authenticate. we could use the Spring Auth Server for this. Its a bit like Keycloak, but more customizable and lighter weight and easy to start with (u can have a working OAuth auth server in ~5 lines of code - a method, annotation, and one line of Java code.
