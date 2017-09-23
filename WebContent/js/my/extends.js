
/**
 * 密码是否相同判断
 */
$.extend($.fn.validatebox.defaults.rules, {
    equals: {
        validator: function(value,param){
            return value == $(param[0]).val();
        },
        message: '两次密码不同'
    }
});