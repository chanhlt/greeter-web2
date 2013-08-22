package org.bhn.training.impl;

import org.bhn.training.api.Greeter;

public class SimpleStringGreeterImpl implements Greeter {
    @Override
    public String greet() {
        return "Hello World!";
    }
}
