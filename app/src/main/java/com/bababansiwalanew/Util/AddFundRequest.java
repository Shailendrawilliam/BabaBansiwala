package com.bababansiwalanew.Util;

public class AddFundRequest {

    String _UMobile;
    String _UPassword;
    String _AccountNo;
    String _TransactionID;
    String _Amount;
    String _FundType;
    String _Remark;
    String _BankName;
    String _AmountINWord;
    String _ImgSlip1;



    public AddFundRequest(  String _UMobile, String _UPassword, String _AccountNo, String _TransactionID, String _Amount, String _FundType, String _Remark, String _BankName, String _AmountINWord, String _ImgSlip1  ) {

        this._UMobile = _UMobile;
        this._UPassword = _UPassword;
        this._AccountNo = _AccountNo;
        this._TransactionID = _TransactionID;
        this._Amount = _Amount;
        this._FundType = _FundType;
        this._Remark = _Remark;
        this._BankName = _BankName;
        this._AmountINWord = _AmountINWord;
        this._ImgSlip1 = _ImgSlip1;

    }
}
