package com.example.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/10 15:43
 **/
@Component
public class ClassA {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClassF classF;

    private ClassC classC;

    private ClassD classD;

    @Autowired
    public void setClassD(ClassD classD) {
        this.classD = classD;
    }

    @Lookup
    public ClassB classb() {
        logger.info(".................");
        return null;
    }


    public ClassA(ClassC c) {
        logger.info("instantiation constructor(ClassC) ........ClassA");
        classC = c;
    }

//    public ClassA() {
//        logger.info("instantiation........ClassA");
//    }

    public void out() {
        classC.out();
        classD.out();
        classF.out();
    }

    public void lookup() {
        logger.info("CLassB hashCode={}", classb().hashCode());
    }
}
