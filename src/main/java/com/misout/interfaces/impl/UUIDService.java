package com.misout.interfaces.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.misout.interfaces.IdService;

@Service(value="uuidService")
public class UUIDService implements IdService {

	@Override
	public String getGlobalId() {
		return UUID.randomUUID().toString();
	}

}
