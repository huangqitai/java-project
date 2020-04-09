const CheckUtil ={
    /**
     * 校验手机号码是否正确
     * @param phone 
     */
    checkPhone: function (phone :string):boolean {
            let reg = /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/;
            return reg.test(phone);
    }
    ,
    
    /**
     * 验证邮箱格式是否正确
     * @param email 
     */
    checkEmail: function (email:string):boolean {
        let reg = /^[a-zA-Z0-9_-]+@([a-zA-Z0-9]+\.)+(com|cn|net|org)$/;
        return reg.test(email);
    },
    
    /**
     * 验证身份证号码
     * @param string 
     * @param type 
     */
    checkIDNumber: function (idNumber : string = '', type = "大陆"):boolean{
        if(idNumber === ''){
            return false;
        }
        if (type === "大陆") {
            let reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
            return reg.test(idNumber);
        }
        if (type === "香港") {
            let reg = /^[A-Z][0-9]{6}\([0-9A]\)$/;
            return reg.test(idNumber);
        }
        if (type === "澳门") {
            let reg = /^[157][0-9]{6}\([0-9]\)$/;
            return reg.test(idNumber);
        }
        if (type === "台湾") {
            let reg = /^[A-Z][0-9]{9}$/;
            return reg.test(idNumber);
        }
    
    }
    
}
