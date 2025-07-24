import reg from '../Css/login.module.css';
function LoginUi(){
    return(
        <div className={reg.main}>
            <h2>Welcome New User</h2>
            <div className={reg.from}>
                <div className={reg.email_div}>
                    <img src='/gmail.png' alt='icon' className={reg.icon} />
                    <input type='email' placeholder='Enter the email id' className={reg.email} />
                </div>
                <div className={reg.pass_div}>
                    <img src='/password.png' alt='icon' className={reg.icon} />
                    <input type='password' placeholder='Enter the Password' className={reg.pass} />
                </div>
                <label className={reg.err}></label>
                <div className={reg.btn}>
                    <input type='button' value={"Login"} className={reg.butn}/>
                </div>
                <div className={reg.det}>
                    <label>I Not have a Account Already ?</label>
                    <a href='/register' className={reg.reg}>Register</a>
                </div>
            </div>
        </div>
    )
}
export default LoginUi;