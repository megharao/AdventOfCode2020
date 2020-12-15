package com.advent.of.code.day14;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Masking {

    private static Map<String, BitSet> memoryMapA = new HashMap<>();
    private static Map<Long, Long> memoryMapB = new HashMap<>();

    public static long runA(String input) {
        Arrays.stream(input.split("mask = "))
                .forEach(s -> handleBlock(s));

        return memoryMapA.values().stream().mapToLong(bitset -> bitset.toLongArray()[0])
                .sum();
    }

    private static List<BitSet> handleBlock(String block) {
        String[] lines = block.split("\n");
        String mask = lines[0];

        return IntStream.range(1, lines.length)
                .mapToObj(i -> getBitSetFromLine(lines[i], mask))
                .collect(Collectors.toList());
    }

    private static BitSet getBitSetFromLine(String line, String mask) {
        // mem[47892] = 675192690
        String[] pieces = line.split("(mem\\[|] = )");
        BitSet bits = getBitSetFromIntString(pieces[2]);
        char[] chars = mask.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(i -> {
                    if(chars[i] == 'x') return;
                    int bitIndex = mask.length() -1 - i;
                    if(chars[i] == '1') bits.set(bitIndex);
                    if(chars[i] == '0') bits.clear(bitIndex);
                });

        memoryMapA.put(pieces[1], bits);
        return bits;

    }

    private static BitSet getBitSetFromIntString(String num) {
        return BitSet.valueOf(new long[] { Integer.parseInt(num) });
    }

    private static BitSet applyMaskToLineWithoutFloats(String line, String mask) {
        String[] pieces = line.split("(mem\\[|] = )");
        BitSet bits = getBitSetFromIntString(pieces[1]);
        char[] chars = mask.toCharArray();
        IntStream.range(0, chars.length)
                .forEach(i -> {
                    if(chars[i] == 'x' || chars[i] == 0) return;
                    int bitIndex = mask.length() -1 - i;
                    if(chars[i] == '1') bits.set(bitIndex);
                });
        return bits;
    }

    public static long runB(String input) {
        Arrays.stream(input.split("mask = "))
                .forEach(s -> handleBlockB(s));
        return memoryMapB.values().stream().mapToLong(Long::longValue).sum();

    }

    private static void applyLineToMemory(String line, String mask){
        BitSet bitSet = applyMaskToLineWithoutFloats(line, mask);
        String[] split = line.split(" = ");
        handleFloatingBits(bitSet, mask, Long.parseLong(split[1]));
    }

    private static void handleFloatingBits(BitSet bitSet, String mask, long value) {
        int x = mask.indexOf('X');
        if(x == -1) {
            memoryMapB.put(bitSet.toLongArray()[0], value);
            return;
        }

        String newMask = mask.substring(0, x) + '_' + mask.substring(x + 1);
        // create floating 1
        BitSet clone1 = (BitSet) bitSet.clone();
        clone1.set(mask.length() - x - 1);
        handleFloatingBits(clone1, newMask, value);

        // create floating 0;
        BitSet clone2 = (BitSet) bitSet.clone();
        clone2.clear(mask.length() - x -1);
        handleFloatingBits(clone2, newMask, value);
    }

    private static void handleBlockB(String block) {
        String[] lines = block.split("\n");
        String mask = lines[0];

        IntStream.range(1, lines.length)
                .forEach(i -> applyLineToMemory(lines[i], mask));

    }

    public static String getInput()  {
        String input = "mask = 001X11X1X010X1X1010XX10X100101011000\n" +
                "mem[43398] = 563312\n" +
                "mem[51673] = 263978\n" +
                "mem[18028] = 544304215\n" +
                "mask = X0100001101XX11100010XX110XX11111000\n" +
                "mem[24151] = 2013\n" +
                "mem[15368] = 19793\n" +
                "mem[45005] = 478\n" +
                "mem[1842] = 190808161\n" +
                "mem[36033] = 987\n" +
                "mem[26874] = 102\n" +
                "mask = 00X0000110110X000110010101XX0X010001\n" +
                "mem[9507] = 7\n" +
                "mem[50019] = 16475608\n" +
                "mem[4334] = 129799\n" +
                "mem[37373] = 182640\n" +
                "mem[28170] = 534617265\n" +
                "mem[6432] = 354252\n" +
                "mem[36752] = 834628\n" +
                "mask = 10100000101101100110X001X0X001100X10\n" +
                "mem[36664] = 30481\n" +
                "mem[6532] = 103013119\n" +
                "mem[45659] = 15629\n" +
                "mem[19533] = 167227\n" +
                "mem[40461] = 344193233\n" +
                "mem[6217] = 26713310\n" +
                "mask = X0XX010100110101X0001101X11100X100X0\n" +
                "mem[38530] = 6202\n" +
                "mem[53032] = 13775\n" +
                "mem[39333] = 1003152\n" +
                "mem[3932] = 1240562\n" +
                "mem[59246] = 12638\n" +
                "mask = 0X1X010100X001X0011000X10000011X11X1\n" +
                "mem[51007] = 43736089\n" +
                "mem[32553] = 977\n" +
                "mem[5131] = 323347526\n" +
                "mem[21451] = 176282356\n" +
                "mem[22857] = 118\n" +
                "mem[50924] = 217\n" +
                "mask = 001111X110100X11X100000011110111X100\n" +
                "mem[3954] = 3854\n" +
                "mem[19628] = 6778501\n" +
                "mem[29233] = 104\n" +
                "mem[18456] = 135287\n" +
                "mem[10018] = 379\n" +
                "mem[14384] = 969770374\n" +
                "mask = 0X1X000110X1X10001100110100X01000001\n" +
                "mem[25112] = 62086\n" +
                "mem[22964] = 2379583\n" +
                "mem[45021] = 1429003\n" +
                "mask = X010XX11X0110110011XX000011000110001\n" +
                "mem[17265] = 180092\n" +
                "mem[36033] = 495818745\n" +
                "mem[28455] = 7765821\n" +
                "mask = 01000X0110110001100X01X1111000XXX001\n" +
                "mem[395] = 37390\n" +
                "mem[6432] = 962\n" +
                "mem[10247] = 130364\n" +
                "mem[10136] = 529\n" +
                "mem[62469] = 62129\n" +
                "mask = 00001100X010X110011X0000111X0X100XX1\n" +
                "mem[8811] = 206575\n" +
                "mem[37066] = 41\n" +
                "mem[53499] = 1505104\n" +
                "mem[22863] = 59636084\n" +
                "mem[50013] = 45392\n" +
                "mem[29757] = 1343911\n" +
                "mask = 001000011010X10X10000001XX011X000010\n" +
                "mem[23068] = 8829046\n" +
                "mem[49194] = 614470096\n" +
                "mask = XX1X000110X101100X101100X01101X00X11\n" +
                "mem[62870] = 3995829\n" +
                "mem[61328] = 18642\n" +
                "mem[50232] = 70531300\n" +
                "mem[48827] = 17923\n" +
                "mem[12416] = 530017\n" +
                "mem[33496] = 181946\n" +
                "mask = 00100X0110XX00011X00000X101100X10100\n" +
                "mem[64825] = 5590\n" +
                "mem[55315] = 3210\n" +
                "mem[532] = 92226\n" +
                "mask = 00X0XX0000100110011010X0X00XX101011X\n" +
                "mem[35112] = 1037772\n" +
                "mem[46051] = 5636\n" +
                "mem[32440] = 5415168\n" +
                "mem[6812] = 64661\n" +
                "mask = XX1111110010110X010XXX0010X0110XX001\n" +
                "mem[2313] = 14107547\n" +
                "mem[57582] = 3420940\n" +
                "mask = 0X100X01101X011101001001111101110X1X\n" +
                "mem[1584] = 1309601\n" +
                "mem[45021] = 142440\n" +
                "mem[52855] = 22177947\n" +
                "mask = 001X1X11XX101101010010X01X00X100X000\n" +
                "mem[51649] = 224687001\n" +
                "mem[30137] = 16118\n" +
                "mem[49157] = 1286\n" +
                "mask = 0011010X001X0101110X01000X11011X0100\n" +
                "mem[8527] = 483\n" +
                "mem[23222] = 60397\n" +
                "mem[47303] = 4597311\n" +
                "mask = 1010001110X00X0010000X100X0X01011100\n" +
                "mem[61912] = 65321\n" +
                "mem[28793] = 217\n" +
                "mem[3216] = 2226\n" +
                "mem[15267] = 196\n" +
                "mem[12210] = 634690438\n" +
                "mask = XX100001100101011XX000001X0X00X10X10\n" +
                "mem[52112] = 232196\n" +
                "mem[5131] = 8215922\n" +
                "mem[21390] = 97675\n" +
                "mem[60773] = 295919\n" +
                "mem[10967] = 188393052\n" +
                "mem[30137] = 40094772\n" +
                "mask = 01100X00001X0111X11001011X000X1010X0\n" +
                "mem[47036] = 8270917\n" +
                "mem[26111] = 3884\n" +
                "mem[48992] = 3941\n" +
                "mem[21396] = 9612429\n" +
                "mask = X01X01X0100X101001XX00001X1101X00110\n" +
                "mem[47036] = 785762\n" +
                "mem[20586] = 91901152\n" +
                "mem[38530] = 338166139\n" +
                "mem[29577] = 753085\n" +
                "mask = X01001010001011100X01XX010010011X000\n" +
                "mem[50548] = 31352881\n" +
                "mem[17969] = 264\n" +
                "mem[12532] = 122897915\n" +
                "mask = 100X0X011011110001001XX0100110010101\n" +
                "mem[7092] = 488918089\n" +
                "mem[5131] = 2146748\n" +
                "mem[13662] = 1422934\n" +
                "mem[54353] = 299758672\n" +
                "mem[17622] = 15998\n" +
                "mem[12416] = 48024869\n" +
                "mem[15520] = 925305185\n" +
                "mask = X010000100X1000X1000X10010X010100010\n" +
                "mem[49608] = 17989\n" +
                "mem[5478] = 192384\n" +
                "mem[7958] = 729\n" +
                "mask = 0010X001X1100X1X1X000100XX10100X1100\n" +
                "mem[47164] = 170643\n" +
                "mem[1049] = 151435402\n" +
                "mem[24631] = 47998921\n" +
                "mask = 101001XX0X1X010110011101000110110111\n" +
                "mem[39780] = 29719\n" +
                "mem[20606] = 714268\n" +
                "mem[40889] = 367330023\n" +
                "mem[6414] = 28304231\n" +
                "mem[63401] = 1417\n" +
                "mask = 1X10010X00X10101100X010110X1100X1001\n" +
                "mem[38445] = 392\n" +
                "mem[14087] = 19086\n" +
                "mem[36110] = 7609\n" +
                "mem[61683] = 24000\n" +
                "mem[55077] = 2975\n" +
                "mem[2109] = 446867\n" +
                "mask = 011000011X010X0X1X100110100001X1XX10\n" +
                "mem[32849] = 150162\n" +
                "mem[22563] = 3985\n" +
                "mem[10602] = 225990962\n" +
                "mask = 00X00X01X00001X1X0000X00101100100110\n" +
                "mem[43197] = 134523909\n" +
                "mem[65396] = 266246531\n" +
                "mem[54292] = 263069\n" +
                "mem[7677] = 99022189\n" +
                "mem[16568] = 15208393\n" +
                "mask = 1X100X011X1000111000XX1100011X00X0X1\n" +
                "mem[2877] = 1577\n" +
                "mem[39731] = 1276\n" +
                "mem[10602] = 844609393\n" +
                "mem[13447] = 4710\n" +
                "mask = 0010X00110XX0101100X00XX100110X10100\n" +
                "mem[10466] = 93198549\n" +
                "mem[21290] = 624\n" +
                "mem[2948] = 51676784\n" +
                "mem[23734] = 6032\n" +
                "mem[29894] = 48902591\n" +
                "mem[271] = 60066\n" +
                "mask = 1011000110X1011X001X11110100110111X0\n" +
                "mem[1843] = 1562\n" +
                "mem[1049] = 9936\n" +
                "mem[14474] = 305948608\n" +
                "mem[40634] = 680784423\n" +
                "mem[9394] = 12344199\n" +
                "mask = 000011X000100X10011X000X001100X10X10\n" +
                "mem[37198] = 9587\n" +
                "mem[40486] = 15533376\n" +
                "mem[28252] = 1625\n" +
                "mem[59079] = 166206\n" +
                "mask = 0010010110110XXXXX0X0001100100000X00\n" +
                "mem[40119] = 168760390\n" +
                "mem[63012] = 1016\n" +
                "mem[6964] = 13134\n" +
                "mem[6116] = 19700991\n" +
                "mem[60039] = 492285\n" +
                "mask = 10X000011X10001X100000111X10000101X0\n" +
                "mem[532] = 16484832\n" +
                "mem[48228] = 18188385\n" +
                "mem[65048] = 14886349\n" +
                "mem[29631] = 1088356\n" +
                "mask = 0100X00110110X00XXX010110X11X111100X\n" +
                "mem[24637] = 561045\n" +
                "mem[62166] = 62287574\n" +
                "mem[395] = 1350\n" +
                "mem[46447] = 15165\n" +
                "mask = 01101XX100110X1X11001X0X0X0X01011110\n" +
                "mem[29481] = 88712206\n" +
                "mem[8052] = 965421\n" +
                "mask = X0X0010100X1X101101X111010X11010X10X\n" +
                "mem[27388] = 41257883\n" +
                "mem[22151] = 2499234\n" +
                "mem[17067] = 1210879\n" +
                "mask = 00100001X0X10X0110000000101XX001010X\n" +
                "mem[148] = 43621\n" +
                "mem[23734] = 243862817\n" +
                "mask = 101000XX1010001110000X1X00011X0X0100\n" +
                "mem[19226] = 7783454\n" +
                "mem[47036] = 32167689\n" +
                "mem[54708] = 28465363\n" +
                "mem[25775] = 13654\n" +
                "mem[38159] = 226030009\n" +
                "mem[33886] = 22797977\n" +
                "mem[47934] = 34738195\n" +
                "mask = 1010000110110111000101011X0XX1X111X1\n" +
                "mem[8015] = 1639518\n" +
                "mem[32888] = 89628061\n" +
                "mem[19414] = 3293870\n" +
                "mem[45803] = 3055\n" +
                "mem[2849] = 517315\n" +
                "mem[7103] = 1807237\n" +
                "mask = 0010010X1X110111011010001X010X10X110\n" +
                "mem[32337] = 14059\n" +
                "mem[7162] = 22418419\n" +
                "mem[62068] = 491160015\n" +
                "mem[52514] = 62411508\n" +
                "mem[21998] = 16113734\n" +
                "mem[14899] = 4165873\n" +
                "mask = 01000101101001100110XX01011XX0X00XX0\n" +
                "mem[63651] = 706\n" +
                "mem[27388] = 269141496\n" +
                "mem[16791] = 90544\n" +
                "mem[58514] = 2084386\n" +
                "mem[6512] = 82029923\n" +
                "mask = XX100X0X1011011X0110X00000011X10XX10\n" +
                "mem[25492] = 51834825\n" +
                "mem[39104] = 11018\n" +
                "mem[31518] = 5721690\n" +
                "mask = X01001X0101101110X10010111X11X110000\n" +
                "mem[16817] = 43478591\n" +
                "mem[49714] = 32182\n" +
                "mem[7715] = 20391\n" +
                "mem[36282] = 511726\n" +
                "mem[2709] = 58604\n" +
                "mask = 10100001101000XX10001001X110001101X0\n" +
                "mem[21290] = 96121933\n" +
                "mem[4581] = 935753770\n" +
                "mem[10322] = 214308733\n" +
                "mem[22563] = 955\n" +
                "mem[21998] = 174320\n" +
                "mask = 001X010010XXXX10011010010XX01X0X0100\n" +
                "mem[27908] = 10394\n" +
                "mem[58731] = 17043901\n" +
                "mem[12207] = 89277\n" +
                "mem[50189] = 70951683\n" +
                "mem[40310] = 1070062397\n" +
                "mask = X11001X11010X1100110X101X0100X0X01X0\n" +
                "mem[48661] = 35809\n" +
                "mem[6512] = 466\n" +
                "mem[22172] = 9259291\n" +
                "mask = 001X0001X001010XX001000010X1001X1111\n" +
                "mem[45021] = 7965\n" +
                "mem[10414] = 132450\n" +
                "mask = X11001111X10111001X001010X001X00011X\n" +
                "mem[22734] = 23954922\n" +
                "mem[18333] = 522531412\n" +
                "mem[21084] = 2928539\n" +
                "mask = 0010000X00110101XX0XX111100100100110\n" +
                "mem[10793] = 30167743\n" +
                "mem[54236] = 15119211\n" +
                "mem[46526] = 34600696\n" +
                "mask = X010010100X10101X0011X0010000X1XXXX1\n" +
                "mem[40874] = 107825637\n" +
                "mem[12207] = 5066\n" +
                "mem[64061] = 12594443\n" +
                "mem[14677] = 104815480\n" +
                "mem[47294] = 27328513\n" +
                "mem[36871] = 99385\n" +
                "mem[55732] = 3825863\n" +
                "mask = 0010X10X101X011101X000011X1100110000\n" +
                "mem[39282] = 9472566\n" +
                "mem[19564] = 55941\n" +
                "mem[8527] = 26084\n" +
                "mem[10265] = 130187\n" +
                "mem[6432] = 865842\n" +
                "mem[20931] = 1702\n" +
                "mask = 00110X0010X01X1001101X0000XX1XX0110X\n" +
                "mem[54465] = 11299\n" +
                "mem[13022] = 487449\n" +
                "mask = 00100XXX101101101101111010X100100000\n" +
                "mem[20710] = 1510193\n" +
                "mem[1742] = 2963920\n" +
                "mem[15368] = 241191\n" +
                "mem[48928] = 8865\n" +
                "mask = 0X10XX0100X10X011000X01101X0X1101110\n" +
                "mem[33496] = 157963055\n" +
                "mem[10527] = 1744363\n" +
                "mem[25912] = 24812738\n" +
                "mem[53894] = 65229499\n" +
                "mem[27656] = 195539\n" +
                "mem[56053] = 84622\n" +
                "mem[58013] = 503836980\n" +
                "mask = 00X0000X10X1011X0X10110011111X000011\n" +
                "mem[21324] = 100568910\n" +
                "mem[11832] = 25433857\n" +
                "mem[15696] = 65297\n" +
                "mask = 00100X010011010110XX01X11XX1001X0111\n" +
                "mem[1742] = 5701\n" +
                "mem[50038] = 1734\n" +
                "mem[3338] = 10181349\n" +
                "mem[64950] = 715735117\n" +
                "mem[3094] = 6261\n" +
                "mask = 01XX01001010X1X101XX000010X111110XX0\n" +
                "mem[30706] = 34032209\n" +
                "mem[57669] = 953918\n" +
                "mem[2368] = 18511\n" +
                "mem[58246] = 14197924\n" +
                "mem[12602] = 3821248\n" +
                "mem[37932] = 73626\n" +
                "mask = 00X0000110X000011XX0000011100X000000\n" +
                "mem[44726] = 577645454\n" +
                "mem[31822] = 2444199\n" +
                "mask = X0100X01X110001X1X0000X01010X0X00111\n" +
                "mem[40204] = 167462\n" +
                "mem[13234] = 334\n" +
                "mem[55553] = 649450\n" +
                "mem[18698] = 152213289\n" +
                "mem[56964] = 1004699\n" +
                "mem[17434] = 557\n" +
                "mask = 0X1000011111XX01X01XX10X0100000000X1\n" +
                "mem[54363] = 171716\n" +
                "mem[27133] = 813977\n" +
                "mem[25112] = 478238\n" +
                "mem[2734] = 2300\n" +
                "mem[23972] = 7597\n" +
                "mask = 0110X1X1X011011XX10010X11100X1111X00\n" +
                "mem[21998] = 2245\n" +
                "mem[39814] = 10501801\n" +
                "mem[16186] = 807\n" +
                "mask = X010000110100001100X0011X11000XX0010\n" +
                "mem[21976] = 1290104\n" +
                "mem[45127] = 1447\n" +
                "mem[19564] = 679\n" +
                "mem[8927] = 40098844\n" +
                "mem[43124] = 2060353\n" +
                "mem[17227] = 11511\n" +
                "mask = X110X00110X10101111000X0000X10111110\n" +
                "mem[25530] = 23237\n" +
                "mem[55910] = 1785756\n" +
                "mem[38723] = 1821559\n" +
                "mem[30849] = 4089\n" +
                "mem[532] = 661\n" +
                "mask = 0X10010XX01X011X011000011X01X1X001X0\n" +
                "mem[17212] = 6523\n" +
                "mem[37424] = 480\n" +
                "mem[40862] = 449969985\n" +
                "mem[28474] = 40994780\n" +
                "mem[21577] = 36128\n" +
                "mem[39066] = 7501680\n" +
                "mask = X100X101100X01X001101001X11001111110\n" +
                "mem[35142] = 186426\n" +
                "mem[28005] = 1296725\n" +
                "mem[57552] = 433183\n" +
                "mem[26566] = 56636\n" +
                "mem[4581] = 1646680\n" +
                "mem[35799] = 2658\n" +
                "mask = 001011010001X00110X00X10000001011X10\n" +
                "mem[13674] = 62138919\n" +
                "mem[63552] = 11168\n" +
                "mem[11669] = 56357099\n" +
                "mask = 101000XX1010XX0010000X10XX1100010010\n" +
                "mem[22434] = 34054009\n" +
                "mem[19261] = 856\n" +
                "mem[24828] = 7024\n" +
                "mem[34924] = 648168\n" +
                "mem[22917] = 9557844\n" +
                "mask = 10100X0X1011111100X1010000000111100X\n" +
                "mem[63552] = 11477437\n" +
                "mem[23072] = 8131648\n" +
                "mem[19002] = 1064\n" +
                "mem[23946] = 183\n" +
                "mem[2440] = 1277\n" +
                "mask = X0100001101X000X100001001X1X00110110\n" +
                "mem[55553] = 49381\n" +
                "mem[25631] = 41125881\n" +
                "mem[62633] = 590643\n" +
                "mask = 0X1000011001010001XX11X0010100000000\n" +
                "mem[10466] = 506832\n" +
                "mem[23072] = 5583\n" +
                "mem[45005] = 8337603\n" +
                "mem[59216] = 7005456\n" +
                "mask = 0XXX000111111001X0100100001000000100\n" +
                "mem[19928] = 48311172\n" +
                "mem[22974] = 815\n" +
                "mem[34266] = 13786112\n" +
                "mem[1742] = 9648313\n" +
                "mem[1094] = 162\n" +
                "mem[55709] = 31320282\n" +
                "mask = 0XX0000110100XX1X001001001X001000001\n" +
                "mem[22346] = 340600\n" +
                "mem[39104] = 935807\n" +
                "mem[64441] = 570\n" +
                "mem[56853] = 3313\n" +
                "mem[22434] = 1892025\n" +
                "mask = 01X000X1X0110XXXX1100110000X00101000\n" +
                "mem[26813] = 23072664\n" +
                "mem[9142] = 282783543\n" +
                "mem[29807] = 14754\n" +
                "mem[56288] = 62827\n" +
                "mask = 01X0010X1XX001X001X010X011100100X100\n" +
                "mem[47774] = 352023\n" +
                "mem[5938] = 132498542\n" +
                "mem[24828] = 8444211\n" +
                "mem[55829] = 238313735\n" +
                "mask = 0010010100X101X10XX0X10X110100XX1X00\n" +
                "mem[1908] = 30255\n" +
                "mem[40461] = 1524854\n" +
                "mem[21752] = 3313\n" +
                "mem[38177] = 164\n" +
                "mem[32888] = 20182288\n" +
                "mem[17656] = 2560835\n" +
                "mask = 01000101101XX11X0110X10X0000X1XX0000\n" +
                "mem[18028] = 6424701\n" +
                "mem[11832] = 73576\n" +
                "mem[18812] = 15408\n" +
                "mask = X010X00XXXX1X1100100X1101X1111110010\n" +
                "mem[31868] = 1008118155\n" +
                "mem[16970] = 560\n" +
                "mem[6414] = 659729\n" +
                "mask = 00100X0110X1010001X0XXX1X00100100X00\n" +
                "mem[60039] = 1335434\n" +
                "mem[22051] = 4352989\n" +
                "mem[23413] = 8881\n" +
                "mem[5131] = 3574\n" +
                "mem[31132] = 1822377\n" +
                "mem[59227] = 3565275\n" +
                "mem[55044] = 629\n" +
                "mask = 101001010001X101100X1XX01X01100X1111\n" +
                "mem[2119] = 6096\n" +
                "mem[25137] = 4534409\n" +
                "mem[34466] = 2697336\n" +
                "mem[24201] = 506176\n" +
                "mem[25286] = 110343\n" +
                "mask = 0XX00001101100011000010X101X0X11X100\n" +
                "mem[29807] = 25323\n" +
                "mem[12207] = 27513971\n" +
                "mem[9003] = 1398544\n" +
                "mem[28341] = 50817018\n" +
                "mem[30137] = 115\n" +
                "mem[42114] = 67247621\n" +
                "mask = 0000X1000010X1X0011010111X0X01X11X10\n" +
                "mem[48228] = 2010329\n" +
                "mem[45718] = 71839\n" +
                "mem[33886] = 136902\n" +
                "mem[51771] = 2015\n" +
                "mask = X000000110X101000100000010010X0011X0\n" +
                "mem[4243] = 33894587\n" +
                "mem[64857] = 8145\n" +
                "mem[45718] = 97465094\n" +
                "mem[53834] = 3359009\n" +
                "mask = 0010X101000101011X0X01X0101000000001\n" +
                "mem[24012] = 379049\n" +
                "mem[39780] = 26758\n" +
                "mem[59983] = 495835\n" +
                "mem[37409] = 1160\n" +
                "mem[52514] = 6321\n" +
                "mem[27459] = 147\n" +
                "mem[41942] = 217105\n" +
                "mask = 00100001100101011X101X0X10111XX10000\n" +
                "mem[17846] = 10118006\n" +
                "mem[59737] = 2963\n" +
                "mem[34644] = 35114650\n" +
                "mem[13172] = 143244\n" +
                "mem[5938] = 51096\n" +
                "mem[44123] = 3352\n" +
                "mask = 001000X1101101X001X001XX00110101X0X0\n" +
                "mem[38177] = 2168495\n" +
                "mem[11075] = 7671\n" +
                "mem[47735] = 2437651\n" +
                "mem[57709] = 103925776\n" +
                "mem[9577] = 253960744\n" +
                "mem[61912] = 713476954\n" +
                "mem[10466] = 509335816\n" +
                "mask = 0X10X00110010100011000001X01001X0001\n" +
                "mem[46526] = 985771\n" +
                "mem[63247] = 474051554\n" +
                "mem[22968] = 581\n" +
                "mem[29811] = 4967030\n" +
                "mem[57544] = 438283695\n" +
                "mem[7042] = 308851\n" +
                "mask = 001XXX010011X10XX100010X1100X0010100\n" +
                "mem[22856] = 10167\n" +
                "mem[25967] = 196716\n" +
                "mem[17344] = 30111\n" +
                "mem[3954] = 21193\n" +
                "mask = 011000XX1101000X00X00101001111000000\n" +
                "mem[45718] = 22050\n" +
                "mem[4315] = 28856671\n" +
                "mem[3954] = 1669054\n" +
                "mask = XXX000011X11X1000XX011X100X100010101\n" +
                "mem[25208] = 62883413\n" +
                "mem[40039] = 460470\n" +
                "mem[27976] = 317910\n" +
                "mem[6549] = 3697104\n" +
                "mem[34078] = 112\n" +
                "mem[62178] = 479428706\n" +
                "mask = 0110000111X1X0X1X0100100XXX001XX0010\n" +
                "mem[532] = 4184102\n" +
                "mem[25575] = 20376\n" +
                "mem[59465] = 35723765\n" +
                "mem[32827] = 2041066\n" +
                "mem[21963] = 519238\n" +
                "mem[56441] = 22508\n" +
                "mask = X0100001101101000100000X0011X01000X0\n" +
                "mem[34078] = 36026\n" +
                "mem[12451] = 602257\n" +
                "mask = 10X0010100X1X10110010010100X0X100011\n" +
                "mem[52291] = 3349730\n" +
                "mem[51550] = 12311148\n" +
                "mem[27235] = 986707194\n" +
                "mem[7958] = 2162\n" +
                "mem[36824] = 3705422\n" +
                "mask = 011X0X0111010000X110000X1X01XX0XX100\n" +
                "mem[21004] = 1994888\n" +
                "mem[10900] = 11111\n" +
                "mem[24854] = 1327\n" +
                "mem[45320] = 1739644\n" +
                "mem[29894] = 1918\n" +
                "mem[62034] = 165719\n" +
                "mask = 1XX10X10100X1010X1000100X0X10X001X00\n" +
                "mem[54431] = 68179\n" +
                "mem[48498] = 269569\n" +
                "mem[25492] = 53144423\n" +
                "mem[24130] = 510\n" +
                "mem[9579] = 22225\n" +
                "mask = 0X00X101101011X00110X0001001010011X0\n" +
                "mem[54156] = 597982\n" +
                "mem[3020] = 27476\n" +
                "mem[18748] = 105524\n" +
                "mem[37066] = 28361301\n" +
                "mem[43484] = 19990814\n" +
                "mem[18698] = 635178\n" +
                "mask = X0100001101000X1100X0110XX11111X1000\n" +
                "mem[44272] = 88008\n" +
                "mem[11075] = 919\n" +
                "mem[41491] = 2905\n" +
                "mem[4898] = 32296\n" +
                "mem[10607] = 10054\n" +
                "mem[28252] = 31037";
        return input;
    }
}