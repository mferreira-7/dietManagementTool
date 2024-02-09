package main.app.utils;

class badSearchException extends Exception {
    public badSearchException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
}