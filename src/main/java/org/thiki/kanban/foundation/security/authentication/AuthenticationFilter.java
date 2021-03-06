package org.thiki.kanban.foundation.security.authentication;

import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.thiki.kanban.foundation.configuration.ApplicationContextProvider;
import org.thiki.kanban.foundation.security.Constants;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by xubt on 10/24/16.
 */
@Service
@ConfigurationProperties(prefix = "security")
public class AuthenticationFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String currentURL = ((RequestFacade) servletRequest).getRequestURI();
        logger.info("authentication start.currentURL:{}", currentURL);
        String localAddress = servletRequest.getLocalAddr();
        String authentication = ((RequestFacade) servletRequest).getHeader(Constants.HEADER_PARAMS_AUTHENTICATION);

        if (isLocalTestEnvironmentAndFreeAuthentication(localAddress, authentication)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String method = ((RequestFacade) servletRequest).getMethod();
        Map<String, Authentication> authProviders = ApplicationContextProvider.getApplicationContext().getBeansOfType(Authentication.class);
        for (Map.Entry entry : authProviders.entrySet()) {
            Authentication authProvider = (Authentication) entry.getValue();
            if (authProvider.getClass().getName().indexOf("org.thiki") > -1 && authProvider.matchPath(currentURL)) {
                authProvider.authenticate(currentURL, method, ((RequestFacade) servletRequest).getHeader("userName"));
            }
        }
        logger.info("authentication end.");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isLocalTestEnvironmentAndFreeAuthentication(String localAddress, String authentication) {
        return Constants.LOCAL_ADDRESS.equals(localAddress) && Constants.FREE_IDENTIFICATION.equals(authentication);
    }
}
