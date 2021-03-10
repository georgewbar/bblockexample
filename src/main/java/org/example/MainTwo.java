package org.example;

import javassist.*;
import javassist.bytecode.*;
import javassist.bytecode.analysis.ControlFlow;

import java.io.IOException;

public class MainTwo {

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, BadBytecode {
        ClassPool pool = ClassPool.getDefault();
        pool.insertClassPath("src/main/java");
        CtClass cc = pool.get("expackage.Ex2");
        CtMethod[] declaredMethods = cc.getDeclaredMethods();
//        ClassFile cf = cc.getClassFile();

        for (CtMethod cm : declaredMethods) {
            MethodInfo minfo = cm.getMethodInfo();
            CodeAttribute ca = minfo.getCodeAttribute();
            CodeIterator ci = ca.iterator();
            ConstPool cp = ca.getConstPool();

            ControlFlow cflowGraph = new ControlFlow(cm);
            ControlFlow.Block[] basicBlocks = cflowGraph.basicBlocks();

            System.out.println(minfo);
            System.out.println("====================================");
            System.out.println();

            // iterate through basic blocks
            for (ControlFlow.Block bb : basicBlocks) {
                System.out.printf("basic block index=%d, pos=%d, length=%d %n", bb.index(), bb.position(), bb.length());
                System.out.println("-----------------------------------------");
                int start = bb.position();
                int end = start + bb.length();

                ci.move(start);
                while(ci.lookAhead() < end) { // no need to use has next
                    int instructionIndex = ci.next();
                    String instruction = InstructionPrinter.instructionString(ci, instructionIndex, cp);
                    System.out.println(instructionIndex + ": " + instruction);
                }

                System.out.println();
            }
            System.out.println();
        }

        cc.writeFile("dump");
    }
}
