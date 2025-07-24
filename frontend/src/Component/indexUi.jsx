import index from '../Css/index.module.css';
function IndexUi(){
    return(
        <div className={index.main}>
            <p className={index.ph}>Welcome to Chats App please select the given Options Below</p>
            <div className={index.det}>
                <a href="/login" className={index.log}>Login</a>
                <label className={index.or}>Or</label>
                <a href="/register" className={index.reg}>Register</a>
            </div>
        </div>
    );
}
export default IndexUi;