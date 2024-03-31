#include <iostream>
#include <algorithm>

using namespace std;

void init(){
    ios_base :: sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);
}

int main(){
    init();
    int N,C;
    cin >> N >> C;
    int num[N];

    for(int i=0; i<N; i++){
        int n;
        cin >> n;
        num[i] = n;
    }

    sort(num,num+N);

    int start = 1;
    int end = num[N-1] - num[0];
    int result = start;

    while(start <= end){ 
        int cnt=1;
        int current = num[0];
        int mid = (start + end)/2;
        bool available = false;
        for(int i=1; i<N; i++){
            if(num[i] - current >= mid){
                if(++cnt == C){
                    available = true;
                    break;
                }
                current = num[i];
            }
        }
        if(available){
            start = mid+1;
            result = mid;
        }else{
            end = mid-1;
        }
    }   

    cout << result;

    return 0;
}