import CardList from "../card/CardList";

const Home = ({tasks}) => {
    return (
       <CardList tasks={tasks}/>
    );
}
export default Home;