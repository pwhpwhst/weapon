#pragma once
#include<unordered_map>
#include<set>
#include<sstream>
#include"../symbols/Env.h"
#include"../symbols/CompileInfo.h"
#include"../symbols/Token.h"
#include"../SLR/Node.h"

class NodeValue{
	public: static const int SYN=0;
	public: static const int INH=1;
	public: Node *node;
	public: int value_type;
	public: NodeValue();
	public: NodeValue(Node *node,int value_type);
	public: virtual ~NodeValue();
};

typedef std::shared_ptr<NodeValue> P_NodeValue;


class SDT_genertor{
public: SDT_genertor();
public: virtual P_NodeValue handle(const P_NodeValue &nodeValue,
	unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo)=0;
public: virtual ~SDT_genertor();
protected: string child(const P_NodeValue &nodeValue,int index,int value_type);
protected: string own(const P_NodeValue &nodeValue,int value_type);
protected: ostringstream os;

};

typedef std::shared_ptr<SDT_genertor> P_SDT_genertor;


class SDT_Factory{
public: static SDT_Factory instance;
public: unordered_map<string,P_SDT_genertor> factory;
public: SDT_Factory();
public: virtual ~SDT_Factory();
};

