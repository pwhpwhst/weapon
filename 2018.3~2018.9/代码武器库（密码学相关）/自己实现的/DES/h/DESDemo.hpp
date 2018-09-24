class DESUtil{

private:
	
	static std::bitset<64> secret_key;
	static int password_substitute1[];
	static int shiftBits[];
	static int password_substitute2[];
	static int IP[];
	static int E[];
	static int S_BOX[8][4][16];
	static int P[];
	static int IP_1[];
	std::bitset<64> oper(std::bitset<64> input,int flag);

public:
	std::bitset<64> encrpt(std::bitset<64> input);
	std::bitset<64> decrpt(std::bitset<64> input);
};


