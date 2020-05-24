#pragma once
#include<unordered_map>
#include"../symbols/Env.h"
#include"../SLR/Node.h"

class SDT_genertor{
public: SDT_genertor();
public: virtual void handle(Env &env,const Node &node)=0;
public: virtual ~SDT_genertor();
};

typedef std::shared_ptr<SDT_genertor> P_SDT_genertor;


class SDT_Factory{
public: static SDT_Factory instance;
public: unordered_map<string,P_SDT_genertor> factory;
public: SDT_Factory();
public: virtual ~SDT_Factory();
};
