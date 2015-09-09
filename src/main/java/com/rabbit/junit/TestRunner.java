package com.rabbit.junit;

import java.util.List;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class TestRunner extends BlockJUnit4ClassRunner {

	public TestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected void collectInitializationErrors(List<Throwable> errors) {

	}

	@Override
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		return new InvokeMethod(method, test);
	}
}
