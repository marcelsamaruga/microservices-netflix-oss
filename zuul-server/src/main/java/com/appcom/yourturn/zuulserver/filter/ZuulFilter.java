package com.appcom.yourturn.zuulserver.filter;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ZuulFilter extends com.netflix.zuul.ZuulFilter {

	@Override
	public boolean shouldFilter() {
		log.debug("Pre filter");
		return false;
	}

	@Override
	public Object run() throws ZuulException {
		log.debug("Run");
		RequestContext context = RequestContext.getCurrentContext();
		log.info(context.toString());
		return null;
	}

	@Override
	public String filterType() {
		log.debug("type");
		return null;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

}
