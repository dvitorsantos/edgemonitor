package lsdi;

import lsdi.Threads.ContextMonitorThread;

public class Main {
    public static void main(String[] args) {
        new ContextMonitorThread().start();
    }
}