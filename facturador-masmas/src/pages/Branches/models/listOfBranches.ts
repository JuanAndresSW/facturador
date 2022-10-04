import branch from "./branch";
type listOfBranches = {
    branches: branch[],
    totalPages: number,
    last: boolean
};
export default listOfBranches;