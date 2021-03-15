package org.example;


import javassist.*;
import javassist.bytecode.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Hello world!
 */
public class MainFour {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, ClassNotFoundException, BadBytecode, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src/main/java");
        CtClass cc = pool.get("expackage.Ex3");
        CtMethod[] declaredMethods = cc.getDeclaredMethods();

        for (CtMethod cm : declaredMethods) {
            MethodInfo minfo = cm.getMethodInfo();
            CodeAttribute ca = minfo.getCodeAttribute();
            ConstPool cp = ca.getConstPool();
            int lengthOfBytecode = ca.getCodeLength();
            int maxLocals = ca.getMaxLocals();

            Bytecode bc = new Bytecode(cp, 1, 0);
//            bc.addStore(maxLocals, pool.get("java.lang.Throwable")); // maybe be unnessacry
            bc.addGetstatic(pool.get("java.lang.System"), "out", Descriptor.of(pool.get("java.io.PrintStream")));
            bc.addLdc("this is great");
            String printlnDesc = Descriptor.ofMethod(CtClass.voidType, new CtClass[]{pool.get("java.lang.String")});
            bc.addInvokevirtual(pool.get("java.io.PrintStream"), "println", printlnDesc);
//            bc.addLoad(maxLocals, pool.get("java.lang.Throwable")); // maybe be unnessacry
            bc.addOpcode(Opcode.ATHROW);
            CodeAttribute newCa = bc.toCodeAttribute();
//            newCa.computeMaxStack();
//            newCa.computeMaxStack();
            CodeIterator ci = ca.iterator();
            ci.append(newCa.getCode());
            ca.computeMaxStack(); // update max stack of ca
//            ca.setMaxLocals(maxLocals + 1); // maybe unnecessary if astore is removed

            ca.getExceptionTable().add(0, lengthOfBytecode, lengthOfBytecode, 0);
//            cm.insertAfter("{ System.out.println(\"hello from javassist\"); }", false);
        }

        cc.writeFile("dump");
        cc.detach();
        Class<?> newEx3Class = pool.get("expackage.Ex3").toClass();
        Object o = newEx3Class.getConstructor().newInstance();

        System.out.println("every thing is fine ");

    }
}
