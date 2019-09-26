package com.dheeraj.ekartv3.modules;

import com.dheeraj.ekartv3.models.AboutMe;
import com.dheeraj.ekartv3.services.implementations.ApiService;
import com.dheeraj.ekartv3.services.Api;
import com.dheeraj.ekartv3.services.implementations.HttpClient;
import com.dheeraj.ekartv3.services.IHttpClient;
import com.dheeraj.ekartv3.util.JwtHelper;
import com.dheeraj.ekartv3.util.StringHelper;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Scopes;
import com.typesafe.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.Environment;

/**
 * @author Dheeraj Reddy
 */
public class SecurityModule extends AbstractModule {

    Logger logger = LoggerFactory.getLogger(SecurityModule.class);

    private Config config;

    @Inject
    public SecurityModule(Environment environment, Config config) {
        this.config = config;
    }

    @Override
    protected void configure() {
        bind(AboutMe.class).asEagerSingleton();
        bind(Api.class).to(ApiService.class).in(Scopes.SINGLETON);
        bind(IHttpClient.class).to(HttpClient.class).in(Scopes.SINGLETON);
        logger.info("Completed class binding for Security Module");
    }
}
