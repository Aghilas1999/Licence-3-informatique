package tpbitcoin;

import org.bitcoinj.core.*;
import org.bitcoinj.script.ScriptBuilder;

import java.math.BigInteger;
import java.util.List;

public class Miner {
    private static int  txCounter;
    private NetworkParameters params;
    public static final long EASY_DIFFICULTY_TARGET = Utils.encodeCompactBits(Utils.decodeCompactBits(Block.EASIEST_DIFFICULTY_TARGET).divide(new BigInteger("1024")));
    public static final long SOMEWHAT_HARDER_DIFFICULTY_TARGET = Utils.encodeCompactBits(Utils.decodeCompactBits(Block.EASIEST_DIFFICULTY_TARGET).divide(new BigInteger("65536")));

    public Miner(NetworkParameters params){
        this.params = params;
    }

    /**
     * find a valid a nonce, e.g such that hash of block header is smaller than the block's target
     * @param block: the block on which to set the nonce
     * @return input block with a valid nonce
     */
    // TODO
    private static Block setValidNonce(Block block){
        return block;
    }

    /* borrowed from bitcoinj.core, not the real thing, for testing only
    * needed for creating a fake coinbase that pass bitcoinj basic verification
    */
    private  static  Transaction generateCoinbase(NetworkParameters params, byte[] pubKey, String amount){
        Transaction coinbase = new Transaction(params);
        final ScriptBuilder inputBuilder = new ScriptBuilder();
        inputBuilder.data(new byte[]{(byte) txCounter, (byte) (txCounter++ >>8)});
        coinbase.addInput(new TransactionInput(params, coinbase,
                inputBuilder.build().getProgram()));
        coinbase.addOutput(new TransactionOutput(params, coinbase, Coin.parseCoin(amount),
                ScriptBuilder.createP2PKOutputScript(ECKey.fromPublicOnly(pubKey)).getProgram()));
        return coinbase;
    }

    /**
     * Create a new block, predecessor of lastBlock. Difficulty of the new bloc kis set to EASY_DIFFICULTY
     * @param lastBlock: the last block of the blockchain
     * @param txs: a list of transactions (may be empty)
     * @param pubKey: the public key of the miner
     * @return
     */
    // TODO
    public Block mine(Block lastBlock, List<Transaction> txs, byte[] pubKey){

        txs.add(generateCoinbase(params, pubKey, "6,25"));
        Block newBlock = new Block(params, lastBlock.getVersion(),lastBlock.getHash(), lastBlock.getMerkleRoot(),
                lastBlock.getTimeSeconds(), Miner.EASY_DIFFICULTY_TARGET,0L, txs);
        newBlock.solve();
        // utilidztion d'un try/catch pour le nonce
        while (true){
        try {
            // utilisation de la fonction de l'enancee.
            newBlock.verifyHeader();
            break;

        }
        catch (VerificationException e) {
            // De meme en ce server de la fonction getNonce si un noces est bien validez.
            newBlock.setNonce(newBlock.getNonce()+1);
        }

       }
        return newBlock;
}
}



