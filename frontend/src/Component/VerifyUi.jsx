import ver from '../Css/verify.module.css';
function VerifyUi(){
    return(
        <div className={ver.main}>
            <h3>Verfication</h3>
            <img src='/OTP.png' alt='OTP icon' className={ver.icon}/>
            <label>We have sent the otp code code to this email id navxxxx@gmail.com</label>
            <label>Please Enter the OTP Code Below</label>
            <label className={ver.err}></label>
            <div className={ver.det}>
                <input type='number' placeholder='Enter the OTP Code here' className={ver.otp}/>
                <input type='submit' value={"Verify"} className={ver.ver}/>
            </div>
        </div>
    )
}
export default VerifyUi;