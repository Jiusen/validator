package com.firesoon.calibrator.check;

public class CheckSurgDiag
{
	// 判断 内科 外科
	public static int check(char c)
	{
        if (c >= 'A' && c <= 'J'){
            return 1; // 外科
        }else if (c >= 'K' && c <= 'Q'){
            return 2; // 非手术室
        }else if (c >= 'R' && c <= 'Z'){
            return 3; // 内科
        }
        return 0;
    }
}
