package lsdi.Threads;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.sun.management.OperatingSystemMXBean;
import lsdi.Models.Location;
import lsdi.Models.Performace;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class ContextMonitorThread extends Thread {
    private static final long MEGABYTE = 1024L * 1024L;
    private final OperatingSystemMXBean osBean;
    private File database;
    private DatabaseReader dbReader;
    private String ip;

    public ContextMonitorThread() {
        try {
            ip = "128.101.101.101";
            database = new File("src/main/java/lsdi/GeoLite2-City_20230331/GeoLite2-City.mmdb");
            dbReader = new DatabaseReader.Builder(database).build();
            osBean = (OperatingSystemMXBean) java.lang.management.ManagementFactory.getOperatingSystemMXBean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Performace performace = getPerformace();
                Location location = getLocation();
                System.out.println(performace.toString());
                System.out.println(location.toString());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException | GeoIp2Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Performace getPerformace() {
        long memory = osBean.getFreeMemorySize() / MEGABYTE;
        double cpu = osBean.getCpuLoad() * 100;

        return new Performace(cpu, memory, 0);
    }

    public Location getLocation() throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        Location location = new Location();
        location.setLatitude(response.getLocation().getLatitude());
        location.setLongitude(response.getLocation().getLongitude());

        return location;
    }
}
