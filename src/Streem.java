import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

class Video {
    private final int id;
    private final int size;

    public Video(int id, int size) {
        this.id = id;
        this.size = size;

    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }
}


class VideoRequest {
    Video v;
    Cache c;
    Endpoint[] e;
    int[] y;
    int n; //number of requests


    public VideoRequest(Video v, Cache c, Endpoint[] e, int n){
        this.v = v;
        this.c = c;
        this.e = e;
        y = new int[e.length];
        this.n = n;
    }

    int computeY(){
        int videoSize = v.getSize();
        for (int i = 0; i < e.length; i++){
            Endpoint currentE = e[i];
            int cacheLatency = e[i].getCacheLatency(c);


        }

    }
}


class Endpoint {
    int endpointNumber;
    HashMap<Integer, Integer> latencies;
    int latencyDatacenter;
    int cacheNumber;

    public int getEndpointNumber() {
        return endpointNumber;
    }

    public int getCacheLatency(int cacheID){
        return latencies.get(cacheID);
    }

    public int getLatencyDatacenter() {
        return latencyDatacenter;
    }

    public int getCacheNumber() {
        return cacheNumber;
    }

    public Endpoint(int ID, int lD, int cN, HashMap<Integer, Integer> lt){
        this.endpointNumber = ID;
        this.latencyDatacenter = lD;
        this.cacheNumber = cN;
        this.latencies = lt;
    }

}

class Cache {
    int size;
    ArrayList<Integer> endpointIDs = new ArrayList<>();
    ArrayList<Integer> latencies = new ArrayList<>();
    ArrayList<Video> videos = new ArrayList<>();

    public Cache(int sz){
        this.size = sz;
    }
}

class SortByY implements Comparator<VideoRequest>{

    @Override
    public int compare(VideoRequest v1 , VideoRequest v2) {
        return v1.computeY() - v2.computeY();
    }
}

public class Streem {

    //global videoReq array
    private VideoRequest[] v;

    Scanner scan = new Scanner(System.in);

    int video;
    int endp;
    int request;
    int cache;
    int sizecache;

    Cache[] caches;
    Video[] videos;
    ArrayList<Endpoint> endpoint;
    VideoRequest[] requests;

    boolean[][] createBooleanMatrix(int E, int Vi){
        //making sure all elemts fit in Matrix
        //may give null pointer exceptions
        boolean[][] matrix = new boolean[E+1][Vi+1];
        return matrix;
    }

    public static void main(String[] args){
        System.out.println("dupex");
    }

    void readInput() {
        video = scan.nextInt();
        endp = scan.nextInt();
        request = scan.nextInt();
        cache = scan.nextInt();
        sizecache = scan.nextInt();

        caches = new Cache[cache];

        for (int i = 0; i < cache; i++) {
            caches[i] = new Cache(sizecache);
        }

        videos = new Video[video];
        for (int i = 0; i < video; i++) {
            videos[i] = new Video(i, scan.nextInt());
        }

        endpoint = new ArrayList<>();
        for (int i = 0; i < endp; i++) {
            int latency = scan.nextInt();
            int Ncache= scan.nextInt();
            HashMap<Integer, Integer> latencies = new HashMap<>();

            for (int j = 0; j < Ncache; j++) {
                latencies.put(scan.nextInt(), scan.nextInt());
            }
            endpoint.add(new Endpoint(i, latency, Ncache, latencies));
        }

        requests = new VideoRequest[request];
        for (int i = 0; i < request; i++) {

        }
    }

    //sorts video request array
    //call this method on the global v array
    void sortVideoRequests(VideoRequest[] videoReqArray){
        SortByY s = new SortByY();
        for(int i=1; i<videoReqArray.length; i++){
            s.compare(videoReqArray[i-1], videoReqArray[i]);
        }
    }

    void videoPerCache() {
        for (int i = 0; i < request; i++) {
            requests[i].c.videos.add(requests[i].v);
        }
    }



}

