package com.pwhTest;

import java.lang.reflect.Method;

class PwhTest{
	public static void main(String args[]){

	Class<?> cl = PasswordUtils.class;

     for (Method m : cl.getDeclaredMethods()) {
         UseCase uc = m.getAnnotation(UseCase.class);
         if (uc != null) {
             System.out.println("Found Use Case:" + uc.id() + " "
                         + uc.description());
         }
     }

		System.out.println("afsadfg");
	}
}