package org.example;


import javassist.*;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class MainThree
{
    public static void main( String[] args ) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src/main/java");
        CtClass cc = pool.get("expackage.Ex3");
        CtMethod[] declaredMethods = cc.getDeclaredMethods();

        for (CtMethod cm : declaredMethods) {
            cm.insertAfter("{ System.out.println(\"hello from javassist\"); }", false);
        }

        cc.writeFile("dump");
    }
}
