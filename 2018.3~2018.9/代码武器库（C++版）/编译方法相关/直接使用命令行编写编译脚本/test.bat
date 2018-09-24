g++ -c grid.cpp
g++ -c mf_code_impl.cpp
g++ -c mf_code_static_impl.cpp
g++ -c test.cpp
g++ -o test grid.o mf_code_impl.o mf_code_static_impl.o test.o -lws2_32

del grid.o
del mf_code_impl.o
del mf_code_static_impl.o
del test.o