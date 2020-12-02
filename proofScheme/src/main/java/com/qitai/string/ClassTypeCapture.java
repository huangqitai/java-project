
package com.qitai.string;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class Building {
	public void sayHi(String name) {
		if (name != "")
			System.out.println(name);
		System.out.println("say hi");
	}
}

class House extends Building {
}

public class ClassTypeCapture<T> {
	Class<T> kind;
	Map<String, Class<?>> anonymousMap = new HashMap<>();

	void addType(String typename, Class<?> kind) {
		anonymousMap.put(typename, kind);
	}

	Class<?> createNew(String typename) throws ClassNotFoundException {
		Class<?> anonymousClass = Class.forName(typename);
		return anonymousClass;
	}

	public ClassTypeCapture(Class<T> kind) {
		this.kind = kind;
	}

	public boolean f(Object arg) {
		return kind.isInstance(arg);
	}

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		ClassTypeCapture<Building> capture = new ClassTypeCapture<>(Building.class);
		System.out.println(capture.f(new Building()));  //true
		System.out.println(capture.f(new House()));    //true
		ClassTypeCapture<House> capture1 = new ClassTypeCapture<>(House.class);
		System.out.println(capture1.f(new Building())); // false
		System.out.println(capture1.f(new House()));  //true

		Class<?> building = capture.createNew("test.Building");
		capture.addType("building", building);
		Class<?> aClass = capture.anonymousMap.get("building");
		//Method sayHi_error = aClass.getMethod("sayHi");
		//Method sayHi = aClass.getMethod("sayHi",Building.class);
		//sayHi.invoke(sayHi, "hello world");
		//
		//System.out.println(aClass);
	}
}
