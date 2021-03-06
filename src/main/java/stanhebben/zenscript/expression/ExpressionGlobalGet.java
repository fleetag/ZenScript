package stanhebben.zenscript.expression;

import org.objectweb.asm.Type;
import stanhebben.zenscript.compiler.IEnvironmentMethod;
import stanhebben.zenscript.symbols.SymbolGlobalValue;
import stanhebben.zenscript.type.*;

public class ExpressionGlobalGet extends Expression {
    
    private final SymbolGlobalValue global;
    
    public ExpressionGlobalGet(SymbolGlobalValue value) {
        super(value.getPosition());
        this.global = value;
    }
    
    @Override
    public ZenType getType() {
        return global.getType();
    }
    
    @Override
    public void compile(boolean result, IEnvironmentMethod environment) {
        environment.getOutput().getStaticField(getOwner(), getName(), getASMDescriptor());
        if(getType().toASMType().equals(Type.getType(Object.class)))
            environment.getOutput().checkCast(getType().getSignature());
    }
    
    public String getOwner() {
        return global.getOwner();
    }
    
    public String getName() {
        return global.getName();
    }
    
    public String getASMDescriptor() {
        return global.getASMDescriptor();
    }
    
}
