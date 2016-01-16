package com.lhy.commons.jms.action;

import java.io.Serializable;

public interface ISerializableMessageAction {
	void execute(Serializable message);
}
