import sys
input = sys.stdin.readline

# 전위 순회
def pre_order(root):
    if root != '.':
        print(root, end = '')    # root
        pre_order(tree[root][0]) # left_child
        pre_order(tree[root][1]) # right_child  
    
# 중위 순회
def in_order(root):
    if root != '.':
        in_order(tree[root][0]) # left_child
        print(root, end = '')    # root
        in_order(tree[root][1]) # right_child
        
# 후위 순회
def post_order(root):
    if root != '.':
        post_order(tree[root][0]) # left_child
        post_order(tree[root][1]) # right_child
        print(root, end = '')    # root
        
N = int(input())
tree = {}
for _ in range(N):
    root,left,right = input().split()
    tree[root] = [left,right] # 자식 노드 저장
    
pre_order('A')
print()
in_order('A')
print()
post_order('A')
print()
