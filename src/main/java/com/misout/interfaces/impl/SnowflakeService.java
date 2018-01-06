package com.misout.interfaces.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misout.interfaces.IdService;
import com.misout.util.SnowflakeIdWorker;

@Service(value="snowflakeService")
public class SnowflakeService implements IdService {
	
	@Autowired
	private SnowflakeIdWorker snowflakeIdWorker;

	@Override
	public String getGlobalId() {
		return String.valueOf(snowflakeIdWorker.nextId());
	}

}
