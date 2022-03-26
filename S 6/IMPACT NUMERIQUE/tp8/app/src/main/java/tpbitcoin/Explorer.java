package tpbitcoin;

import org.bitcoinj.core.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HexFormat;

public class Explorer {
    public final  String baseURL;
    private final HttpClient client;

    public Explorer() {
        this("https://blockchain.info/");
    }

    /**
     * Constructor
     * @param baseURL : the base URL of the web API
     */
    public Explorer(String baseURL){
        this.baseURL = baseURL;
        this.client = HttpClient.newHttpClient();
    }

    /**
     * Query the URL formed by baseURL+suffix with method GET
     * @param suffix: suffix to be added to the base URL to form the query endpoint
     * @return the body of the result of the query
     */
    private String request(String suffix){

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL+suffix)).build();

        System.out.println("REQUEST :" + request);
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            System.err.println("IO error " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrupted " + e.getMessage());
        }
        return response.body();
    }


        /**
     * Current reward for mining a block
     * @return current reward for mining a block in BTC
     */
    public double getBtcReward(){
        String answer = request("q/bcperblock");
        return Double.parseDouble(answer);
    }

    public double getDifficulty () {
        String answer = request("q/getdifficulty");
        return  Double.parseDouble(answer);
    }

    /**
     * Current price of 1BTC
     * @return price of 1BTC in $
     */
    public double getBtcPrice(){
        String answer = request("q/24hrprice");
        return Double.parseDouble(answer);
    }


    public String getLatestHash(){

        return request("q/latesthash");

    }


    // TODO 
    /**
     * Return the raw bytes of the block whose hash is given as argument
     * @param hash of a valid block contained in bitcoin mainnet
     * @return byte array encoding the block
     */
    public byte[] getRawblockFromHash(String hash){
        return hexStringToByte(request("rawblock/"+hash+"?format=hex"));
    }

    /**
     * Create an instance of bitcoinj.core.Block that correspond to the block
     * whose hash is given.
     * @param params : NetworkParameters of the blockchain
     * @param hash : a valid hash of a block 
     * @return  instance of bitcoinj.core.Block representing the block with given hash 
     */
	
    public Block getBlockFromHash(NetworkParameters params, String hash){
        return fromRawblockToBlock(params, getRawblockFromHash(hash));
    }



    /**
     * Convert a block in raw form (array of bytes) to a  bitcoinj Block object
     * @param params : NetworkParameters of the blockchain
     * @param rawblock : bytes encoding the block
     * @return : an instance of bitcoinj.Block reprensenting rawblock
     */
    public Block fromRawblockToBlock(NetworkParameters params, byte[]  rawblock){
        MessageSerializer serializer = new BitcoinSerializer(params,true);
        return new Block(params, rawblock, serializer, Message.UNKNOWN_LENGTH);
    }

    /**
     * Converts a hexadecimal string into the corresponding byte array
     * @param hexString a string representing a sequence of byte, in hexadecimal
     * @return the corresponding byte array
     */
    private static byte[] hexStringToByte(String hexString) {
        return HexFormat.of().parseHex(hexString);
    }


}
