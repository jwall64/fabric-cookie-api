package dev.jwall64.cookieapi.test;


import org.jspecify.annotations.NonNull;

import net.minecraft.gametest.framework.GameTestHelper;
import net.fabricmc.fabric.api.gametest.v1.CustomTestMethodInvoker;
import net.fabricmc.fabric.api.gametest.v1.GameTest;

import java.lang.reflect.Method;

public class GameTests implements CustomTestMethodInvoker {

    @Override
    public void invokeTestMethod(GameTestHelper helper, @NonNull Method method) throws ReflectiveOperationException {
        method.invoke(this, helper);
    }

    @GameTest
    public void dummy(GameTestHelper helper) { 
        helper.succeed();
    }
}
