DATA SECTION
text     db 'Hello x64!', 0
caption  db 'My First x64 Application', 0

CODE SECTION
START:
sub rsp,28h
xor r9d,r9d
lea r8, caption
lea rdx, text
xor rcx,rcx
call MessageBoxA
add rsp,28h
ret