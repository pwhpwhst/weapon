#pragma once
// symbol/Tag.h
// #include <unordered_map>
using namespace std;


class Tag{

public: const static int TYPE=200;
public: const static int INT=201;
public: const static int ID=202;
public: const static int DEFAULT=203;
public: Tag();

public: virtual ~Tag();
};
