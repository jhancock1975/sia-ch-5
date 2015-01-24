package com.rootser.reflection;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReflectorService {

	public static Object getPrivateField(Object o, String fieldName) {
		// Check we have valid arguments...
		assertTrue(o != null);
		assertTrue(fieldName != null);

		// Go and find the private field...
		final Field fields[] = o.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; ++i) {
			if (fieldName.equals(fields[i].getName())) {
				try {
					fields[i].setAccessible(true);
					return fields[i].get(o);
				} catch (IllegalAccessException ex) {
					fail("IllegalAccessException accessing " + fieldName);
				}
			}
		}
		fail("Field '" + fieldName + "' not found");
		return null;
	}

	public static Object invokePrivateMethod(Object o, String methodName,
			Object[] params) {
		// Check we have valid arguments...
		assertTrue(o != null);
		assertTrue(methodName != null);
		assertTrue(params != null);

		// Go and find the private method...
		final Method methods[] = o.getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; ++i) {
			if (methodName.equals(methods[i].getName())) {
				try {
					methods[i].setAccessible(true);
					return methods[i].invoke(o, params);
				} catch (IllegalAccessException ex) {
					fail("IllegalAccessException accessing " + methodName);
				} catch (InvocationTargetException ite) {
					fail("InvocationTargetException accessing " + methodName);
				}
			}
		}
		fail("Method '" + methodName + "' not found");
		return null;
	}

	public List<String> getSetterNames(Object o) {
		ArrayList<String> result = new ArrayList<String>();
		Method methods[] = o.getClass().getDeclaredMethods();
		for (Method m : methods) {
			result.add(m.getName());
		}
		return result;
	}
}
