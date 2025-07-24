import reg from '../Css/register.module.css';
function RegisterUi(){
    return(
        <div className={reg.main}>
            <h2>Welcome New User</h2>
            <div className={reg.from}>
                <div className={reg.email_div}>
                    <img src='/gmail.png' alt='icon' className={reg.icon} />
                    <input type='email' placeholder='Enter the email id' className={reg.email} />
                </div>
                <div className={reg.name_div}>
                    <img src='/user.png' alt='icon' className={reg.icon} />
                    <input type='text' placeholder='Enter the user name' className={reg.name} />
                </div>
                <div className={reg.pass_div}>
                    <img src='/password.png' alt='icon' className={reg.icon} />
                    <input type='password' placeholder='Create your Password' className={reg.pass} />
                </div>
                <label className={reg.err}></label>
                <div className={reg.btn}>
                    <input type='button' value={"Create Account"} className={reg.butn}/>
                </div>
                <div className={reg.det}>
                    <label>I have Account Already ?</label>
                    <a href='/login' className={reg.log}>Login</a>
                </div>
            </div>
        </div>
    )
}
export default RegisterUi;