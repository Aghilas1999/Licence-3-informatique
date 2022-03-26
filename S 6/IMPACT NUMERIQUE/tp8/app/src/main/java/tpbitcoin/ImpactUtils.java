package tpbitcoin;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ImpactUtils {



    /**
     * computes the expected time (in seconds) for mining a block
     * @param hashrate: miner hashing capacity (hash/s)
     * @param difficultyAsInteger: block difficulty, as BigInteger (256 bits integer)
     * @return expected time in seconds before finding a correct block
     */
    // La probabilité de miner un hash .....

    // Q6
    public static BigInteger expectedMiningTime(double hashrate, BigInteger difficultyAsInteger){

        System.out.println("Le niveau de defficulté est :" + difficultyAsInteger);
        System.out.println("La longeur de hash est :" + difficultyAsInteger.longValue());
        BigInteger ValMax = new BigInteger("2").pow(256);
        System.out.println(ValMax);
        BigInteger Tries = ValMax.divide(difficultyAsInteger);

        return  Tries.divide(BigDecimal.valueOf(hashrate).toBigInteger());
    }

    /**
     * Compute the total hashrate of the network given current difficulty level
     * @param difficultyAsInteger: difficulty level as 256bits integer
     * @return hashrate of the network in GH/s
     */

    public static BigInteger  globalHashRate(BigInteger difficultyAsInteger){

        BigInteger NumbertoTries = new BigInteger("2").pow(256).divide(difficultyAsInteger);



        return NumbertoTries.divide(new BigInteger("256"));

    }

    /**
     * Compute the total energy consumption of the network
     * assuming each miner has the same hashrate, and consume the same power
     * @param minerHashrate: the hashrate of each miner, in GH/s
     * @param minerPower: the power consumption of each miner, in Watts
     * @param networkHashrate : the global hashrate of the network
     * @param duration : in second
     * @return energy consumed during duration, in kWh
     */
    public static BigInteger globalEnergyConsumption(BigInteger minerHashrate, long minerPower, BigInteger networkHashrate, long duration){

        BigInteger hashcapacite = minerHashrate.multiply(new BigInteger("10").pow(10));
        BigInteger NombreMiners = hashcapacite.divide(networkHashrate);
        BigInteger totalEnerger = NombreMiners.multiply(new BigInteger(String.valueOf(minerPower)));

        return totalEnerger.multiply(new BigInteger(String.valueOf(duration/1000)));
    }





}
