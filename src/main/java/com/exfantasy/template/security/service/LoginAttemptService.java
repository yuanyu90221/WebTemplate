package com.exfantasy.template.security.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Ref. http://genchilu-blog.logdown.com/posts/745182
 * 
 * @author tommy.feng
 *
 */
@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 2;
    private final int blockTimeMins = 1;
    private LoadingCache<String, Integer> blockList;

	public LoginAttemptService() {
		blockList 
			= CacheBuilder.newBuilder().expireAfterWrite(blockTimeMins, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

    public void loginSucceeded(String key) {
        blockList.invalidate(key);
    }

    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = blockList.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        blockList.put(key, attempts);
    }

    public boolean isBlocked(String key) {
        try {
            return blockList.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
