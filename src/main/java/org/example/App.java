package org.example;


import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.MethodInfo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src/main/java");
        CtClass cc = pool.get("expackage.ExampleClass");
        CtMethod[] declaredMethods = cc.getDeclaredMethods();

        for (CtMethod cm : declaredMethods) {
            cm.insertAfter("{ System.out.println(\"hello from javassist\"); }", false);
        }

        cc.writeFile("dump");
    }
}
