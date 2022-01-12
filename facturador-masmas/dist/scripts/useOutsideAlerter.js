import { useEffect } from "react";
export default function useOutsideAlerter(ref) {
    useEffect(function () {
        function handleClickOutside() {
            if (ref.current && !ref.current.contains(document)) {
                alert("You clicked outside of me!");
            }
        }
        // Bind the event listener
        document.addEventListener("mousedown", handleClickOutside);
        return function () {
            // Unbind the event listener on clean up
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, [ref]);
}
