// Generated by jextract

package c.lib.math;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$23 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$23() {}
    static final MemorySegment const$0 = RuntimeHelper.lookupGlobalVariable("__QNANF", JAVA_DOUBLE);
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "__fpclassifyl",
        constants$20.const$1
    );
    static final FunctionDescriptor const$2 = FunctionDescriptor.of(JAVA_INT,
        JAVA_FLOAT
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "__fpclassifyf",
        constants$23.const$2
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "__fpclassify",
        constants$20.const$1
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "__isnan",
        constants$20.const$1
    );
}

