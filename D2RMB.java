import java.util.*;
public class D2RMB{
	private String[] HanStr = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	private String[] HanUnit = {"拾","佰","仟"};

// 分离整数和小数成为字符串，小数取整到2位整数。 
	private String[] divide(double dNum){
		long lZheng = (long)dNum;
		long lXiao = Math.round((dNum-lZheng)*100);
		//用枚举初始化,数值+"""成为Stringt。
		return new String[] { lZheng+"",String.valueOf(lXiao) };
	}
	//
    // 将一个数值字符串转换为中文读法的字符串。
	// 输入的原串长度应小于等于4.
	// bflag=true,插入中文单位，否则不加单位。
	private String toHanStr(String sNum,boolean bflag){
		String sResult = "";
		int iLength = sNum.length();
		if(iLength <=4 ){
			for(int i = 0; i < iLength; i++){
				int iNum = sNum.charAt(i) - 48; // equ:int iNum = (int)sNum.charAt(i) - 48; 
					if((i < iLength-1) && (iNum!=0) && bflag ){
						sResult += HanStr[iNum] + HanUnit[iLength - 2 - i]; 
					}
					else{
						sResult += HanStr[iNum];
					}
			}

			}
		return sResult;
	}

	 // 如果超过4位，需要进行高4位和低4位的处理. 
	private String toHanType(String sNum,boolean bflag){
		String sResult = "";
		int iCount = sNum.length();
		String s1 = "",s2 = ""; //用于临时存放被分成2部分的字符串。

		if( iCount > 4){
			s1 = sNum.substring(0,iCount - 4); // 存放高4段。 
			sResult += toHanStr(s1,bflag) + "万";
			s2 = sNum.substring(iCount - 4,iCount); // 存放低4段。 
			sResult += toHanStr(s2,bflag);
		}
		else{
			sResult = toHanStr(sNum,bflag);
		}
		return sResult;
	}
	
	private String RMB2Cn(String[] sRMB){
		String s = "" ;
		s = toHanType(sRMB[0],true);
		s += "点" + toHanType(sRMB[1],false);
		return s;

	}


	public static void main(String[] args){
		D2RMB RmbTest = new D2RMB();
		double dRmb = 6745809.896;
		String[] sRmbNum = RmbTest.divide(dRmb);
		System.out.println(Arrays.toString(sRmbNum));
		System.out.println(RmbTest.RMB2Cn(sRmbNum));
	}
}
