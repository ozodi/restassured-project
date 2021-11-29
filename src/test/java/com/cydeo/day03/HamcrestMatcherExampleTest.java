package com.cydeo.day03;


import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class HamcrestMatcherExampleTest {

    /**
     * Hamcrest is a framework for writing matcher objects allowing ‘match’ rules
     * to be defined declaratively.
     * It is an assertion library that can be used additionally to make assertion
     * readable and it comes with a lot of pre-written matchers to make it easier to write and read
     *
     * The method chain of RestAssured then section use the hamcrest matcher library
     * extensively to assert the response components in one chain
     * RestAssured dependency already contains Hamcrest Assertion library
     * so no separate dependency needed
     * All we need is to add static imports and start using it's matchers
     * to make the assertions great again
     *        // import static org.hamcrest.MatcherAssert.assertThat; (will not need this with then section of ResAssured)
     *         // import static org.hamcrest.Matchers.*;
     * Here is the javadoc for all matchers with examples
     * http://hamcrest.org/JavaHamcrest/javadoc/2.2/org/hamcrest/Matchers.html
     */

    @Test
    public void testNumbers(){

        // in junit 5 assertions  3 + 6 = 9
        // import static org.junit.jupiter.api.Assertions.assertEquals;
        assertEquals( 9 , 3+6) ;

        // Hamcrest , it can be written in this way
        // import static org.hamcrest.MatcherAssert.assertThat;
        // import static org.hamcrest.Matchers.*;

        assertThat( 3 + 6 ,  equalTo(9 )  ) ;
        // Hamcrest use matchers to express the intention clearly,
        // it's coming from  org.hamcrest.Matchers.*
        // for equality , there are options like
        /**
         * equalTo(some value)
         * is(some value)
         * is( equalTo(somevalue)  )   // just for readability
         */
        assertThat(4+6 , is(10) );
        /**
         * number comparison :
         * greaterThan()  lessThan()
         */
        assertThat(  5+6 , greaterThan(10)  );

        assertThat(10+10 , lessThanOrEqualTo(20) ) ;

    }

    @Test
    public void testString(){

        String msg = "B23 is Excellent!";
        assertThat(msg,  is("B23 is Excellent!")    ) ;

        assertThat(msg,  equalTo("B23 is Excellent!")    ) ;
        assertThat(msg,  equalToIgnoringCase("b23 is excellent!") );

        assertThat(msg,  startsWith("B23") );
        assertThat(msg,  startsWithIgnoringCase("b23")  );

        assertThat(msg, endsWith("Excellent!") ) ;
        assertThat(msg, endsWithIgnoringCase("excellent!") ) ;

        assertThat(msg, containsString("is")  ) ;
        assertThat(msg, containsStringIgnoringCase("IS") ) ;

        // How to say not in Hamcrest Matchers
        assertThat(msg,  not("B24 is Excellent!")    ) ;
        // one matcher can be wrapped inside another matcher
        // because all matchers method has overloaded version that accept another matcher as argument
        assertThat(msg,  is(    not("B24 is Excellent!")  )  ) ;


        // check if a string is blank: meaning it only have white space " "

        assertThat(msg, not( blankString()  ));

    }

    @Test
    public void testCollections(){
        List<Integer> numberList = Arrays.asList( 3,5,1,77,44,76 ); // 6 element here

        //check this list has size of 6
        assertThat(numberList, hasSize(6));

        //check this list has item of 77
        assertThat(numberList, hasItem(77));

        //check this list has item of 5,44
        assertThat(numberList, hasItems(5,44));

        // check this has items greater than 50
        assertThat(numberList, hasItem(greaterThan(50)));

        //check this list every item is greatThan 0
        assertThat(numberList, everyItem(greaterThan(0)) );

    }




}
